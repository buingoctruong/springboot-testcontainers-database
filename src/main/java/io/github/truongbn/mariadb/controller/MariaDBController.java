package io.github.truongbn.mariadb.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.truongbn.mariadb.entity.Celebrity;
import io.github.truongbn.mariadb.repository.CelebrityRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mariadb")
@RequiredArgsConstructor
public class MariaDBController {
    private final CelebrityRepository celebrityRepository;
    @GetMapping("/{id}")
    public Celebrity getByCelebrityId(@PathVariable("id") int id) {
        return celebrityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Celebrity Not Found: " + id));
    }

    @GetMapping(path = "/pageable")
    public Page<Celebrity> getPage(@ParameterObject @PageableDefault(size = 20,
            sort = "celebrityId", direction = Sort.Direction.ASC) Pageable pageable) {
        return celebrityRepository.findAll(pageable);
    }
}
