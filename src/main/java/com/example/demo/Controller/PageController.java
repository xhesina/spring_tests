package com.example.demo.Controller;

import com.example.demo.UserModel.Days;
import com.example.demo.UserModel.Detyra;
import com.example.demo.UserModel.User;
import com.example.demo.UserRepository.UserRep;
import com.example.demo.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRep userRep;
    @RequestMapping(value = "/regjistrim",method = RequestMethod.GET)
    public ModelAndView rregjistrimView(){
        ModelAndView model=new ModelAndView();
        model.setViewName("regjistrim");
        return model;
    }
    @RequestMapping(value = "/regjistrim",method = RequestMethod.POST)
    public ModelAndView rregjistrim(@RequestParam Map<String,String> usr){
        User user=new User(usr.get("fName"),usr.get("lName"),usr.get("username"),usr.get("email"),usr.get("password"));
        userService.Shto(user);
        ModelAndView model=new ModelAndView();
        String mesazh="U rregjistruat me sukses";
        model.addObject("mesazh",mesazh);
        model.setViewName("login");
        return model;

    }
    @RequestMapping(value = "/schedule",method = RequestMethod.GET)
    public ModelAndView sekretView(Principal principal){
        ModelAndView modelAndView=new ModelAndView();
        List<Days> ditet= Arrays.asList(userRep.findByUsername(principal.getName()).getDays());
        modelAndView.addObject("dita",ditet);
        modelAndView.setViewName("show");
        return  modelAndView;
    }
    @RequestMapping(value = "/login" ,method=RequestMethod.GET)
    public ModelAndView loginView(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping("/detyreform")
    public ModelAndView detyreForm(){
        return new ModelAndView("welcome");
    }
    @RequestMapping(value = "/shtodetyre",method = RequestMethod.GET)
    public String shtoDetyre(@RequestParam Map<String,String > form,Principal principal){
        User user=userRep.findByUsername(principal.getName());
        userService.shtoDetyre(form,user);
        return "redirect:/schedule";


    }
    @RequestMapping(value = "/delete", method=RequestMethod.GET)
    public String fshiDetyre(@RequestParam(value = "dayname",defaultValue = "")String dayname,@RequestParam(value = "index",defaultValue = "")int index,
                             Principal principal){
        User user=userRep.findByUsername(principal.getName());
        userService.fshiDetyre(dayname,index,user);
        return "redirect:/schedule";
    }
    @RequestMapping("/edit")
    public ModelAndView editDuty1(@RequestParam Map<String,String> g,Principal principal){
        int dita;
        switch (g.get("dayname")){
            case "E hene": dita=0;break;
            case "E marte": dita=1;break;
            case "E merkure": dita=2;break;
            case "E enjte": dita=3;break;
            case "E premte": dita=4;break;
            case "E shtune": dita=5;break;
            case "E diel": dita=6;break;
            default:dita=-1;
        }
        User user=userRep.findByUsername(principal.getName());
        Detyra det=user.getDays()[dita].getDuties().get(Integer.parseInt(g.get("index")));
        ModelAndView model=new ModelAndView("edit");
        model.addObject("task",det);
        return model;
    }
    @RequestMapping(value="/editDuty",method=RequestMethod.GET)
    public String editDuty(@RequestParam Map<String,String> form,Principal principal){
        User user=userRep.findByUsername(principal.getName());
        System.out.println(user);
        userService.editDuty(user,form);
        return"redirect:/schedule";
    }

}
