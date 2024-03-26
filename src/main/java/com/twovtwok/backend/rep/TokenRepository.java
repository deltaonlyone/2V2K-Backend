package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token getTokenByUserId(Long userId);
    void deleteByUserId(Long userId);
    Token findTokenByValue(String value);
}
