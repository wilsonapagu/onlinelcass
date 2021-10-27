package com.bipro.ocm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/main")
public class MainController {

//    @Autowired
//    private UserService userService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home (Model model){
        System.out.println(555555);
        return "/home";
    }

    @RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
    public String hello (Model model){
        System.out.println(555555);
        return "/hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String create (Model model){
        System.out.println(555555);
        return "jsp/welcome";
    }

    @RequestMapping(value = { "/view" }, method = RequestMethod.GET)
    public String create2(Model model) {
//        model.addAttribute("leaveType",new LeaveType());
        return "leave/leaveForm";
    }

//    //    @ResponseBody
//    @RequestMapping(value = "/view", method = RequestMethod.GET)
//    public String viewAllUser(Model model) {
//        System.out.println(55555);
////      return null;
//      userService.getAllUsers();
//        return "login";
//    }
//
//
////    @RequestMapping(value = "/hello", method = RequestMethod.GET)
////    public String create(Model model) {
////        System.out.println(55555555);
////        return "login";
////    }
//
//    @GetMapping(path="/all")
//    public @ResponseBody
//    Iterable<Users> findAll() {
//        // This returns a JSON or XML with the users
//        return userService.getAllUsers();
//    }
//
//    	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model, String error, String logout) {
//		if (error != null)
//			model.addAttribute("errorMsg", "Your username and password are invalid.");
//
//		if (logout != null)
//			model.addAttribute("msg", "You have been logged out successfully.");
//
//		return "user/login";
//	}


}
