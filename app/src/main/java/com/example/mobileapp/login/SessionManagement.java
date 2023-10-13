/**
 * This file contains the SessionManagement class which is responsible for managing user sessions in the mobile app.
 */
package com.example.mobileapp.login;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY_ID = "session_user";
    String SESSION_KEY_NAME = "session_user_name";
    String SESSION_KEY_NIC = "session_user_nic";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        // Save session of user whenever user is logged in
        int id = user.getId();
        String name = user.getName();
        String nic = user.getNic();

        editor.putInt(SESSION_KEY_ID, id);
        editor.putString(SESSION_KEY_NAME, name);
        editor.putString(SESSION_KEY_NIC, nic);
        editor.apply(); // Use apply() for asynchronous saving
    }

    public int getSession(){
        // Return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY_ID, -1);
    }

    public String getSessionName(){
        // Return the user name whose session is saved
        return sharedPreferences.getString(SESSION_KEY_NAME, "");
    }

    public String getSessionNIC(){
        // Return the user name whose session is saved
        return sharedPreferences.getString(SESSION_KEY_NIC, "");
    }

    public void removeSession(){
        // Remove the session of the logged-in user
        editor.remove(SESSION_KEY_ID);
        editor.remove(SESSION_KEY_NAME);
        editor.remove(SESSION_KEY_NIC);
        editor.apply(); // Use apply() for asynchronous saving
    }
}
