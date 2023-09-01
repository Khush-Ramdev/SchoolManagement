package com.osttra.service;

import com.osttra.repository.UserRepository;
import com.osttra.to.UserTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService  {

    @Autowired
    UserRepository repository;
    public int register(UserTO user){
        if(user.getRole().equalsIgnoreCase("admin")){
            user.setApproved(true);
        }
        return repository.add(user);
    }


    public int login(UserTO userOld){
        UserTO userNew = repository.getUser(userOld);
        int res = -1;
        if(userNew.getName() != null && userNew.getPassword() != null && userNew.getRole() != null ){
            if(userNew.isApproved()){
                res = 0;
            }
            else{
                res =1;
            }
        }
        else{
            res = 2;
        }
        return res;
    }

    public int update(UserTO userOld){
        UserTO userNew = repository.updateUser(userOld);
        int res =-1;
        if(userNew.getName() != null && userNew.getPassword() != null && userNew.getRole() != null ){
                res =0;
        }
        else{
            res = 1;
        }
        return res;
    }

//    public UserTO getUser(String Email){ return repository.getUser(Email);}

    public int delete(UserTO user) {
        return repository.deleteUser(user);
    }

    public ArrayList<UserTO> getAllUsers(){
        return repository.getAllUsers();
    }

    public UserTO getUser(String email){
       return repository.getUser(email);
    }

    public int ApproveUser(UserTO user){ return repository.ApproveUser(user);}
}
