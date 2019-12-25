package guru.springframework.spring5reactivemongorecipeapp.boostrap;

import guru.springframework.spring5reactivemongorecipeapp.domain.*;
import guru.springframework.spring5reactivemongorecipeapp.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CategoryReactiveRepository categoryRepository;

    @Autowired
    private RecipeReactiveRepository recipeRepository;

    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureRepository;

    @Autowired
    private IngredientReactiveRepository ingredientRepository;

    @Autowired
    private NotesReactiveRepository notesRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (categoryRepository.count().block() == 0) {
            loadCategories();
        }

        if (unitOfMeasureRepository.count().block() == 0) {
            loadUom();
        }

        if (recipeRepository.count().block() == 0) {
            loadRecipe();
        }
    }

    private void loadCategories() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1).block();

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2).block();

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3).block();

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4).block();
    }

    private void loadUom() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1).block();

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2).block();

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3).block();

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMeasureRepository.save(uom4).block();

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5).block();

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Each");
        unitOfMeasureRepository.save(uom6).block();

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");
        unitOfMeasureRepository.save(uom7).block();

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescription("Dash");
        unitOfMeasureRepository.save(uom8).block();
    }

    private void loadRecipe() {

        //get UOMs
        Mono<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (eachUomOptional.block() == null) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Mono<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (tableSpoonUomOptional.block() == null) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Mono<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (teaSpoonUomOptional.block() == null) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Mono<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (dashUomOptional.block() == null) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Mono<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (pintUomOptional.block() == null) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Mono<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (cupsUomOptional.block() == null) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.block();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.block();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.block();
        UnitOfMeasure dashUom = dashUomOptional.block();
        UnitOfMeasure pintUom = pintUomOptional.block();
        UnitOfMeasure cupsUom = cupsUomOptional.block();

        //get Categories
        Mono<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (americanCategoryOptional.block() == null) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Mono<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (mexicanCategoryOptional.block() == null) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.block();
        Category mexicanCategory = mexicanCategoryOptional.block();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(1);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        notesRepository.save(guacNotes).block();
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        Ingredient ingredient1 = new Ingredient("ripe avocados", new BigDecimal(2), eachUom);
        ingredientRepository.save(ingredient1).block();
        Ingredient ingredient2 = new Ingredient("Kosher salt", new BigDecimal(".5"), teapoonUom);
        ingredientRepository.save(ingredient2).block();
        Ingredient ingredient3 = new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom);
        ingredientRepository.save(ingredient3).block();
        Ingredient ingredient4 = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom);
        ingredientRepository.save(ingredient4).block();
        Ingredient ingredient5 = new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom);
        ingredientRepository.save(ingredient5).block();
        Ingredient ingredient6 = new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom);
        ingredientRepository.save(ingredient6).block();
        Ingredient ingredient7 = new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom);
        ingredientRepository.save(ingredient7).block();
        Ingredient ingredient8 = new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom);
        ingredientRepository.save(ingredient8).block();

        guacRecipe.getIngredients().add(ingredient1);
        guacRecipe.getIngredients().add(ingredient2);
        guacRecipe.getIngredients().add(ingredient3);
        guacRecipe.getIngredients().add(ingredient4);
        guacRecipe.getIngredients().add(ingredient5);
        guacRecipe.getIngredients().add(ingredient6);
        guacRecipe.getIngredients().add(ingredient7);
        guacRecipe.getIngredients().add(ingredient8);

        recipeRepository.save(guacRecipe).block();

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");
        tacosRecipe.setServings(4);
        tacosRecipe.setSource("Simply Recipes");
        tacosRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        notesRepository.save(tacoNotes).block();
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        Ingredient ingredient9 = new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient10 = new Ingredient("Dried Oregano", new BigDecimal(1), teapoonUom);
        Ingredient ingredient11 = new Ingredient("Dried Cumin", new BigDecimal(1), teapoonUom);
        Ingredient ingredient12 = new Ingredient("Sugar", new BigDecimal(1), teapoonUom);
        Ingredient ingredient13 = new Ingredient("Salt", new BigDecimal(".5"), teapoonUom);
        Ingredient ingredient14 = new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom);
        Ingredient ingredient15 = new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom);
        Ingredient ingredient16 = new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom);
        Ingredient ingredient17 = new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient18 = new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom);
        Ingredient ingredient19 = new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom);
        Ingredient ingredient20 = new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom);
        Ingredient ingredient21 = new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom);
        Ingredient ingredient22 = new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom);
        Ingredient ingredient23 = new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom);
        Ingredient ingredient24 = new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom);
        Ingredient ingredient25 = new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom);
        Ingredient ingredient26 = new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom);
        Ingredient ingredient27 = new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom);

        ingredientRepository.save(ingredient9).block();
        ingredientRepository.save(ingredient10).block();
        ingredientRepository.save(ingredient11).block();
        ingredientRepository.save(ingredient12).block();
        ingredientRepository.save(ingredient13).block();
        ingredientRepository.save(ingredient14).block();
        ingredientRepository.save(ingredient15).block();
        ingredientRepository.save(ingredient16).block();
        ingredientRepository.save(ingredient17).block();
        ingredientRepository.save(ingredient18).block();
        ingredientRepository.save(ingredient19).block();
        ingredientRepository.save(ingredient20).block();
        ingredientRepository.save(ingredient21).block();
        ingredientRepository.save(ingredient22).block();
        ingredientRepository.save(ingredient23).block();
        ingredientRepository.save(ingredient24).block();
        ingredientRepository.save(ingredient25).block();
        ingredientRepository.save(ingredient26).block();
        ingredientRepository.save(ingredient27).block();

        tacosRecipe.getIngredients().add(ingredient9);
        tacosRecipe.getIngredients().add(ingredient10);
        tacosRecipe.getIngredients().add(ingredient11);
        tacosRecipe.getIngredients().add(ingredient12);
        tacosRecipe.getIngredients().add(ingredient13);
        tacosRecipe.getIngredients().add(ingredient14);
        tacosRecipe.getIngredients().add(ingredient15);
        tacosRecipe.getIngredients().add(ingredient16);
        tacosRecipe.getIngredients().add(ingredient17);
        tacosRecipe.getIngredients().add(ingredient18);
        tacosRecipe.getIngredients().add(ingredient19);
        tacosRecipe.getIngredients().add(ingredient20);
        tacosRecipe.getIngredients().add(ingredient21);
        tacosRecipe.getIngredients().add(ingredient22);
        tacosRecipe.getIngredients().add(ingredient23);
        tacosRecipe.getIngredients().add(ingredient24);
        tacosRecipe.getIngredients().add(ingredient25);
        tacosRecipe.getIngredients().add(ingredient26);
        tacosRecipe.getIngredients().add(ingredient27);

        recipeRepository.save(tacosRecipe).block();

    }
}
