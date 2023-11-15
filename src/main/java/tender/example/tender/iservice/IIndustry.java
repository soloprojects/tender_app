package tender.example.tender.iservice;

import tender.example.tender.dto.IndustryRequest;
import tender.example.tender.entity.Industry;

import java.util.List;

public interface IIndustry {

    Industry findById(Long Id);

    List<Industry> findAll();

    void create(IndustryRequest requestDto);

    Industry update(IndustryRequest requestDto);

    void delete(Long Id);

    void deleteMultiple(List<Long> Id);

}
