package com.jhuoose.foodaholic.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.w3c.dom.Text;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private  MutableLiveData<String> InvitationCode;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Your friend has sent you an invitation!\n Invitation Code is ");
        InvitationCode = new MutableLiveData<>();
        InvitationCode.setValue("3D475C");
    }


    public LiveData<String> getText() {
        MutableLiveData<String> retText = new MutableLiveData<>();
        mText.setValue("Your friend has sent you an invitation!\n Invitation Code is ");
        InvitationCode.setValue("3D478C");
        retText.setValue(mText.getValue() + InvitationCode.getValue());
        return retText;
    }
}