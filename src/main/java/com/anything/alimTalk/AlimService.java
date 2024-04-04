package com.anything.alimTalk;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
//@Transactional
public class AlimService implements IAlimService{
    private AlimRepository alimRepository;
    @Override
    public List<AlimDto> list(AlimDto alimDto) {
        return alimRepository.list(alimDto);
    }

    @Override
    public int insertAction(AlimDto alimDto) {
        return alimRepository.insertAction(alimDto);
    }

    @Override
    public int updateAction(AlimDto alimDto) {
        return alimRepository.updateAction(alimDto);
    }
}
