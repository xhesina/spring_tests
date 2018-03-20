package com.example.demo.UserService;

import com.example.demo.UserModel.CurrUserDetails;
import com.example.demo.UserModel.Days;
import com.example.demo.UserModel.Detyra;
import com.example.demo.UserModel.User;
import com.example.demo.UserRepository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRep userRep;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRep.findByUsername(username);
        if (user!=null){
            CurrUserDetails curruser=new  CurrUserDetails(user);
            return  curruser;
        }
        return null;
    }
    public void Shto(User user){
        if (userRep.findByEmail(user.getEmail())==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRep.save(user);
        }
        else System.out.print("User Eshte me pare");
    }
    public void shtoDetyre(Map<String,String> form,User user){
        String dita=form.get("dayName");
        int index=Integer.parseInt(dita);
        Days[] ditet=user.getDays();
        ditet[index].shtoDetyre(new Detyra(form.get("date"),form.get("duties")));
        user.setDays(ditet);
        userRep.save(user);

    }

    public void fshiDetyre(String dayname, int index, User user) {
        int dita;
        switch (dayname){
            case "E hene": dita=0;break;
            case "E marte": dita=1;break;
            case "E merkure": dita=2;break;
            case "E enjte": dita=3;break;
            case "E premte": dita=4;break;
            case "E shtune": dita=5;break;
            case "E diel": dita=6;break;
            default:dita=-1;
        }
        Days[] days=user.getDays();
        days[dita].getDuties().remove(index);
        user.setDays(days);
        userRep.save(user);
    }
    public void editDuty(User user,Map<String,String> form){
        int dita;
        switch (form.get("dayname")){
            case "E hene": dita=0;break;
            case "E marte": dita=1;break;
            case "E merkure": dita=2;break;
            case "E enjte": dita=3;break;
            case "E premte": dita=4;break;
            case "E shtune": dita=5;break;
            case "E diel": dita=6;break;
            default:dita=-1;
        }
        String date=form.get("date");
        String duty=form.get("duties");
        user.getDays()[dita].getDuties().get(Integer.parseInt(form.get("index"))).setDuties(duty);
        user.getDays()[dita].getDuties().get(Integer.parseInt(form.get("index"))).setDate(date);
        userRep.save(user);

    }
}
