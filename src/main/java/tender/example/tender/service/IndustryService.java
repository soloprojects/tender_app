package tender.example.tender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tender.example.tender.dto.IndustryRequest;
import tender.example.tender.entity.Industry;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.iservice.IIndustry;
import tender.example.tender.repository.IndustryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IndustryService implements IIndustry {

    private final IndustryRepository industryRepo;

    @Override
    public Industry findById(Long Id) {
        Industry industry = industryRepo.findById(Id)
                .orElseThrow(() -> new BusinessException("Data not found", HttpStatus.NOT_FOUND));
        return industry;
    }

    @Override
    public List<Industry> findAll() {

        List<Industry> industries = industryRepo.findAll();
        return industries;
    }

    @Override
    public void create(IndustryRequest requestDto) {
        Industry industry = Industry.builder()
                .name(requestDto.getName())
                .details(requestDto.getDetails())
                .build();
        industryRepo.save(industry);
    }

    @Override
    public Industry update(IndustryRequest requestDto) {
        Industry data = this.findById(requestDto.getId());
        data.setName(requestDto.getName());
        data.setDetails(requestDto.getDetails());
        this.industryRepo.save(data);
        return data;
    }

    @Override
    public void delete(Long Id) {
        industryRepo.deleteById(Id);
    }

    @Override
    public void deleteMultiple(List<Long> idList) {
          industryRepo.deleteAllById(idList);
    }
}
