package com.example.mymedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;

import com.esotericsoftware.kryo.io.Input;
import com.example.mymedicine.model.medicine;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private String name, company, Price, date_manu, price, frequencytaking,gat;
    private medicine med ;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText product_name, product_company, manufacturing_date, pro_price, Frequency_of_taking;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference ProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        gat=getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Medicines");



        AddNewProductButton=findViewById(R.id.add_new_product);
        product_name=findViewById(R.id.product_name);
        product_company=findViewById(R.id.product_company);
        manufacturing_date=findViewById(R.id.manufacturing_date);
        pro_price=findViewById(R.id.price);
        Frequency_of_taking=findViewById(R.id.Frequency_of);
        InputProductImage= findViewById(R.id.select_product_image);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();

            }
        });
        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// name, company, date_manu, price, frequencytaking;
                name = product_name.getText().toString().trim();
                company = product_company.getText().toString().trim();
                date_manu = manufacturing_date.getText().toString().trim();
                price  = pro_price.getText().toString().trim();
                frequencytaking = Frequency_of_taking.getText().toString().trim();
                med=new medicine( company, name, date_manu, price,  frequencytaking);
                if (ImageUri == null)
                {
                 //   Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(name ))
                {
                 //   Toast.makeText(this, "Please write product name ...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(company))
                {
                 //   Toast.makeText(this, "Please write product company...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(date_manu))
                {
                //    Toast.makeText(this, "Please write product date_manu...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(price))
                {
                   // Toast.makeText(this, "Please write product price...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(frequencytaking))
                {
                   // Toast.makeText(this, "Please write product frequency of taking...", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    productRandomKey=name ;
                    final StorageReference filepath = ProductImagesRef.child( ImageUri.getLastPathSegment()+ productRandomKey+".jpg");
                    final UploadTask uploadtask =filepath.putFile(ImageUri);
                    uploadtask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String message = e.toString();
                            Toast.makeText(AdminAddNewProductActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AdminAddNewProductActivity.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();
                            Task<Uri> urlTask = uploadtask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful())
                                    {
                                        throw task.getException();
                                    }

                                    downloadImageUrl = filepath.getDownloadUrl().toString();
                                    return filepath.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful())
                                    {
                                        downloadImageUrl = task.getResult().toString();

                                        Toast.makeText(AdminAddNewProductActivity.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                                        SaveProductInfoToDatabase();
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });

    }
    private void  SaveProductInfoToDatabase(){/// name, company, Price, date_manu, frequencytaking;
        HashMap<String, Object> medmap = new HashMap<>();
        medmap.put("pid", productRandomKey);
        medmap.put("name", name);
        medmap.put("company", company);
        medmap.put("price ", price);
        medmap.put("category", gat);
        medmap.put("image", downloadImageUrl);
        medmap.put("date manu", date_manu);
      //  ProductsRef = database.getReference();
        ProductsRef.child(productRandomKey).updateChildren(medmap);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
    private void OpenGallery(){
        Intent galleryintent= new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent, GalleryPick);

    }

}