package com.hz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.domain.Student;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class MyController {

    @RequestMapping(value = "/returnString-view.do" )
    public String doReturnString(HttpServletRequest request){

        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));

        System.out.println("doReturnString方法，参数name="+name+" age="+age);

        request.setAttribute("myname", name);
        request.setAttribute("myage" , age);
        return "show";
    }

    @RequestMapping(value = "/returnString-view2.do" )
    public String doReturnString2(HttpServletRequest request){

        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));

        System.out.println("doReturnString方法，参数name="+name+" age="+age);

        request.setAttribute("myname", name);
        request.setAttribute("myage" , age);
        return "/WEB-INF/view/show.jsp";
    }

    @RequestMapping(value = "/returnVoid-ajax.do" )
    public Void doReturnVoid(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {

        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));

        System.out.println("doReturnString方法，参数name="+name+" age="+age);

        //处理ajax，使用json做数据的格式
        String json = "";
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        if(student != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转换的json=====" +json);
        }

        //输出数据 响应请求
        httpResponse.setContentType("application/json;charset=utf-8");
        PrintWriter pw = httpResponse.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();

        return null;
    }

    @RequestMapping(value = "/returnStudent-ajax.do" )
    @ResponseBody
    public Student doReturnStudent(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {

        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));

        System.out.println("doReturnString方法，参数name="+name+" age="+age);

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return student;
    }

    @RequestMapping(value = "/returnList-ajax.do" )
    @ResponseBody
    public List<Student> doReturnList(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {

        List<Student> studentList = new ArrayList<>();

        String name = request.getParameter("name");
        int age = Integer.valueOf(request.getParameter("age"));

        System.out.println("doReturnString方法，参数name="+name+" age="+age);

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        studentList.add(student);

        Student student1 = new Student();
        student1.setName("hz");
        student1.setAge(50);
        studentList.add(student1);

        return studentList;
    }

    @RequestMapping(value = "/returnString-ajax.do", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doReturnString(HttpServletRequest request, HttpServletResponse httpResponse) {

        return "返回字符串表示数据，hello";
    }

    @RequestMapping(value = "/test/testpath.do")
    public ModelAndView testPath(HttpServletRequest request, HttpServletResponse httpResponse) {

        System.out.println("testpath");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index.jsp");

        return mv;
    }
}
