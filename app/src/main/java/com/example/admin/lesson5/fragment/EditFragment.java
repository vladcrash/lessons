package com.example.admin.lesson5.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.admin.lesson5.IMyAidlInterface;
import com.example.admin.lesson5.R;
import com.example.admin.lesson5.service.StorageService;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {
    private EditText editText;
    private Callback callback;
    private IMyAidlInterface aidl;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder localService) {
            aidl = IMyAidlInterface.Stub.asInterface(localService);
            setText();
            setCursorToTheEnd();
            showKeyboard();
        }

        private void setText() {
            try {
                editText.setText(aidl.read());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void showKeyboard() {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }

        private void setCursorToTheEnd() {
            editText.requestFocus();
            editText.setSelection(editText.getText().length());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidl = null;
        }
    };

    public interface Callback {
        void done();
    }

    public static EditFragment newInstance() {
        return new EditFragment();
    }

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity should implement Callback interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().bindService(StorageService.newIntent(getActivity()), connection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.edit_text);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unbindService(connection);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                saveData();
                hideKeyboard();
                callback.done();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveData() {
        try {
            aidl.write(editText.getText().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
