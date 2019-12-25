package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import guru.springframework.spring5reactivemongorecipeapp.repository.RecipeReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private RecipeReactiveRepository recipeRepository;

    @Autowired
    public ImageServiceImpl(RecipeReactiveRepository recipeService) {

        this.recipeRepository = recipeService;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        Mono<Recipe> recipeMono = recipeRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] byteObjects = new Byte[0];
                    try {
                        byteObjects = new Byte[file.getBytes().length];

                        int i = 0;

                        for (byte b : file.getBytes()) {
                            byteObjects[i++] = b;
                        }

                        recipe.setImage(byteObjects);

                        return recipe;

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });

        recipeRepository.save(recipeMono.block()).block();

        return Mono.empty();
    }
}
