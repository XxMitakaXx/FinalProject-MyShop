//package org.example.finalprojectmyshop.user.web;
//
//import org.example.finalprojectmyshop.product.models.entities.Category;
//import org.example.finalprojectmyshop.product.service.CategoryService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Set;
//
//@Controller
//public class HomeController {
//
//    private final CategoryService categoryService;
//
//    public HomeController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping("/")
//    public ModelAndView home(ModelAndView modelAndView) {
//
//        modelAndView.setViewName("home");
//
//        Set<Category> categories = this.categoryService.getAllCategories();
//        modelAndView.addObject("categories", categories);
//
//
//        return modelAndView;
//    }
//
//}
