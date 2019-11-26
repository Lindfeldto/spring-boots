package com.example.demowebthymeleaf.web;

import java.util.List;

import javax.validation.Valid;

import com.example.demowebthymeleaf.domain.Customer;
import com.example.demowebthymeleaf.service.CustomerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    // equivalent to model.addAttribute("customerForm", new CustomerForm())
    @ModelAttribute
    CustomerForm setUpForm() {
        return new CustomerForm();
    }

    @GetMapping
    String index(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "customers/index";
    }

    @GetMapping("/{id}/edit")
    String edit(@PathVariable Integer id, CustomerForm form) {
        Customer customer = customerService.findOne(id);
        BeanUtils.copyProperties(customer, form);

        return "customers/edit";
    }

    @PostMapping
    String create(@Valid CustomerForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return index(model);
        }

        // another choice: Dozer, ModelMapper
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customerService.save(customer);

        return "redirect:/customers";
    }

    @PostMapping(path = "/{id}", params = "_method=put")
    String update(@PathVariable Integer id, @Valid CustomerForm form, BindingResult result) {
        if (result.hasErrors()) {
            return edit(id, form);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setId(id);
        customerService.save(customer);

        return "redirect:/customers";
    }

    @PostMapping(path = "/{id}", params = "_method=delete")
    String destroy(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
