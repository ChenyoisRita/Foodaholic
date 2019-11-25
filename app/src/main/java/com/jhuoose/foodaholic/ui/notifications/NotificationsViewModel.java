package com.jhuoose.foodaholic.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jhuoose.foodaholic.model.Notification;

import org.w3c.dom.Text;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private  MutableLiveData<String> InvitationCode;
//    private NotificationController notificationController;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Your friend has sent you an invitation!\n Invitation Code is ");
        InvitationCode = new MutableLiveData<>();
        InvitationCode.setValue("3D475C");
//        notificationController = new NotificationController();
    }


    public LiveData<String> getText() {
        MutableLiveData<String> retText = new MutableLiveData<>();
        mText.setValue("Your friend has sent you an invitation!\n Invitation Code is ");
        InvitationCode.setValue("3D478C");
        retText.setValue(mText.getValue() + InvitationCode.getValue());
        return retText;
    }

    public LiveData<String> getEventText(String eid){
//        Notification notification = notificationController.getDataFromFirebase(eid);
        MutableLiveData<String> retText = new MutableLiveData<>();
//        retText.setValue("An event is approaching.\nEvent Title is " + notification.getEventTitle()
//                            + "\nDate is: " + notification.getDate()
//                            + "\nStart Time is: " + notification.getStartTime()
//                            +  "\nLocation is:" + notification.getLocation());
        return retText;
    }
}