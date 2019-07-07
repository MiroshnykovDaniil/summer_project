package com.project.demo.controller;

import com.project.demo.model.Project;
import com.project.demo.model.Report;
import com.project.demo.repository.ProjectInfoRep;
import com.project.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        // (1) (en)
        // After user login successfully.
        // (vi)
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        System.out.println("User Name: " + userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }
        return "403Page";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String managerPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "managerPage";
    }

    @RequestMapping(value = "/engineer", method = RequestMethod.GET)
    public String engineerPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "engineerPage";
    }

    @RequestMapping(value = "/lead", method = RequestMethod.GET)
    public String leadPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "leadPage";
    }

    @RequestMapping(value = "/viewEffort", method = RequestMethod.GET)
    public String viewEffortPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "viewEffortPage";
    }

    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String createProjectPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
       // model.addAttribute("createProject", userInfo);
        model.addAttribute("project",new Project());
        return "createProjectPage";
    }
    @Autowired
    private EntityManager em,en;

    @Transactional
    @RequestMapping(value="/createProject", method = RequestMethod.POST)
    public String createProjectSubmit(@ModelAttribute Project project){
        String s="INSERT into project.project (name, short_name, description, initial_effort, time_start, time_end, risks,project_ID) VALUES ("+
                "\""+project.getProjectName()+"\""+","+"\""+project.getShortName()+"\""+","+"\""+project.getDescription()+"\""+","+project.getInitialEffort()+
                ","+"\""+project.getTimeStart()+"\""+","+"\""+project.getTimeEnd()+"\""+","+"\""+project.getRisks()+"\""+","+project.getProjectId()+")";
        em.createNativeQuery(s).executeUpdate();
        return "managerPage";
    }

    @Autowired
    ProjectInfoRep projectInfoRep;
    @RequestMapping(value = "/createReport", method = RequestMethod.GET)
    public String createReportPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("project",new Report());
        model.addAttribute("report",new Report());
        List<String>projectName= em.createNativeQuery("select project.name from project.project where 1").getResultList();
        System.out.println(projectName);
        model.addAttribute("projectNames",projectName);
        return "createReportPage";
    }

    @Transactional
    @RequestMapping(value="/createReport", method = RequestMethod.POST)
    public String createReportSubmit(@ModelAttribute Report report, @ModelAttribute Project project,Principal principal){
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        System.out.println(project.getProjectName());
      //  System.out.println(report.getProjectName());
      //  System.out.println(report.getEffort());
        List<Integer> id = em.createNativeQuery("SELECT project.project_id from project.project where name = \""+report.getProjectName()+"\" ").getResultList();
        System.out.println(id);
        List<Integer> userId = em.createNativeQuery("SELECT app_user.user_id from project.app_user where user_name =\""+loginedUser.getUsername()+"\"").getResultList();
        System.out.println(userId);
        en.createNativeQuery("INSERT into project.report (user_id, report_id, activity_type, description, project_id, effort, time_end, time_start,project_name)VALUES (?,?,?,?,?,?,?,?,?)")
                .setParameter(1,userId.get(0))
                .setParameter(2,0)
                .setParameter(3,report.getActivityType())
                .setParameter(4,report.getDescription())
                .setParameter(5,id.get(0))
                .setParameter(6,report.getEffort())
                .setParameter(7,report.getTimeEnd())
                .setParameter(8,report.getTimeStart())
                .setParameter(9,report.getProjectName())
                .executeUpdate();
        return "userInfoPage";
    }

    @RequestMapping(value = "/reviewProject", method = RequestMethod.GET)
    public String reviewProjectPage(Model model, Principal principal, @ModelAttribute Project project) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("project",new Project());
       // List<String>projectName= em.createNativeQuery("select project.name from project.project where 1").getResultList();
       // System.out.println(projectName);
       // model.addAttribute("projectNames",projectName);
        model.addAttribute("list",projectInfoRep.findAll());
        return "projectReviewPage";
    }
/*
    @RequestMapping(value = "/reviewProject", method = RequestMethod.POST)
    public String reviewProjectPagePost(Model model, Principal principal, @ModelAttribute Project project) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        System.out.println(project.getName());
        //System.out.println(projectInfoRep.findById(project.getProjectId()));
        model.addAttribute("list",projectInfoRep.findAllByIdName(project.getName()));
        return "projectReviewPage";
    }*/

}