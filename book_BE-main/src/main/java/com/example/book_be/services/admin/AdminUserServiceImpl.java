package com.example.book_be.services.admin;

import com.example.book_be.bo.UserBo;
import com.example.book_be.dao.NguoiDungRepository;
import com.example.book_be.entity.NguoiDung;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public Page<NguoiDung> findAll(UserBo model) {
        Pageable pageable = PageRequest.of(model.getPage(), model.getPageSize());
        return nguoiDungRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return builder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    @Override
    public NguoiDung save(UserBo bo) {
        return null;
    }

    @Override
    public NguoiDung update(UserBo bo) {
        return null;
    }

    @Override
    public NguoiDung delete(Long id) {
        return null;
    }

    @Override
    public NguoiDung findById(Long id) {
        return null;
    }
}
