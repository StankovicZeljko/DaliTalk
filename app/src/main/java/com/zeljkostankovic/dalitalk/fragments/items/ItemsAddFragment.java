package com.zeljkostankovic.dalitalk.fragments.items;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zeljkostankovic.dalitalk.R;
import com.zeljkostankovic.dalitalk.modules.Item;
import com.zeljkostankovic.dalitalk.viewModels.ItemViewModel;

import java.io.ByteArrayOutputStream;
import java.util.Objects;


public class ItemsAddFragment extends Fragment {

    private ItemViewModel itemViewModel;
    ImageView imageView;
    ActivityResultLauncher<Intent> cameraResult;
    FirebaseStorage storage;
    TextInputLayout description;
    TextInputLayout task;
    Button saveBtn;
    Button cancelBtn;
    Button cameraBtn;
    Button chooseBtn;
    boolean picIsSet = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         cameraResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

             if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                 Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                 imageView.setImageBitmap(bitmap);
                 picIsSet = true;
             }

         });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items_add, container, false);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        storage = FirebaseStorage.getInstance();
        cameraBtn = view.findViewById(R.id.itemAddCameraBtn);
        imageView = view.findViewById(R.id.itemAddImageView);
        description = view.findViewById(R.id.itemAddInputLayoutDesc);
        task = view.findViewById(R.id.itemAddInputLayoutTask);
        saveBtn = view.findViewById(R.id.itemAddSaveBtn);
        cancelBtn = view.findViewById(R.id.itemAddCancelBtn);
        chooseBtn = view.findViewById(R.id.itemAddChooseBtn);

        checkPermissionCamera();

        cameraBtn.setOnClickListener(v -> takePic());
        cancelBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_itemsAddFragment_to_itemsListFragment));
        saveBtn.setOnClickListener(this::saveItem);

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();

        return view;
    }

    private void saveItem(View view) {
        Item item = new Item();
        String desc = Objects.requireNonNull(description.getEditText()).getText().toString();
        String ta = Objects.requireNonNull(task.getEditText()).getText().toString();

        if(validateItemForm(desc, ta)) {
            uploadImgAndGetUrl(desc, item, ta);
            Navigation.findNavController(view).navigate(R.id.action_itemsAddFragment_to_itemsListFragment);

        } else {
            Toast.makeText(requireContext(), "Bitte füllen Sie alles aus", Toast.LENGTH_LONG).show();
        }


    }

    private void uploadImgAndGetUrl(String description, Item item, String task) {

        StorageReference file = storage.getReference().child(description + ".jpg");
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        byte[] data = out.toByteArray();

        file.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        item.setImageUrl(uri.toString());
                        item.setTask(task);
                        item.setDescription(description);
                        itemViewModel.insert(item);
                        Toast.makeText(requireContext(), "Erfolgreich hinzugefügt", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(e -> Toast.makeText(requireContext(), "Upload fehlgeschlagen", Toast.LENGTH_LONG).show());


    }

    private void takePic() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraResult.launch(cameraIntent);
    }

    private void checkPermissionCamera() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

    }

    private boolean validateItemForm(String dec, String tas) {
        boolean result = false;

        if(picIsSet && !dec.isEmpty() && !tas.isEmpty()) {
            result = true;
        }

        return result;
    }

}



