package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getLanguage(@PathVariable(name = "name") String name) {
        for(Language l : this.languages){
            if(l.getName().equals(name)){
                return l;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language name) {
        this.languages.add(name);
        return name;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable(name = "name") String name, @RequestBody Language language) {
        for (Language l : this.languages) {
            if (l.getName().equals(name)) {
                l.setName(language.getName());
                return l;
            }
        }
        return null;
    }
    @DeleteMapping("/{name}")
    public Language delete(@PathVariable (name = "name") String name) {
        for (Language l : this.languages){
            if(l.getName().equals(name)){
                this.languages.remove(l);
                return l;
            }
        }
        return null;
    }

}