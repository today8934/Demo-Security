package com.example.demosecurity.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demosecurity.User;
import com.example.demosecurity.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demosecurity.Ingredient;
import com.example.demosecurity.Ingredient.Type;
import com.example.demosecurity.Order;
import com.example.demosecurity.Taco;
import com.example.demosecurity.data.IngredientRepository;
import com.example.demosecurity.data.TacoRepository;

//tag::injectingDesignRepository[]
//tag::injectingIngredientRepository[]
@Controller
@RequestMapping("/design")
//end::injectingIngredientRepository[]
@SessionAttributes("order")
//tag::injectingIngredientRepository[]
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  //end::injectingIngredientRepository[]
  private TacoRepository tacoRepo;

  private final UserRepository userRepo;

  //end::injectingDesignRepository[]
  /*
  //tag::injectingIngredientRepository[]
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  //end::injectingIngredientRepository[]
   */
  //tag::injectingDesignRepository[]

  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo,
        UserRepository userRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "design")
  public Taco design() {
    return new Taco();
  }

  //end::injectingDesignRepository[]

  //tag::injectingIngredientRepository[]

  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }

    model.addAttribute("taco", new Taco());

    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }
  //end::injectingIngredientRepository[]

//tag::injectingDesignRepository[]
  @PostMapping
  public String processDesign(
      @Valid Taco taco, Errors errors,
      @ModelAttribute Order order) {

    if (errors.hasErrors()) {
      return "design";
    }

    Taco saved = tacoRepo.save(taco);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

//end::injectingDesignRepository[]

  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

  /*
  //tag::injectingDesignRepository[]
  //tag::injectingIngredientRepository[]

   ...
  //end::injectingIngredientRepository[]
  //end::injectingDesignRepository[]
  */

//tag::injectingDesignRepository[]
//tag::injectingIngredientRepository[]

}
//end::injectingIngredientRepository[]
//end::injectingDesignRepository[]
