package {{ config.packagePath }}.service;

import java.util.List;
import org.springframework.stereotype.Service;
import {{ config.packagePath }}.vo.{{ config.firstNameCapital }};

@Service
public interface {{ config.firstNameCapital }}Service {
    
    int deleteByPrimaryKey(String id);

    int insert({{ config.firstNameCapital }} record);

    int insertSelective({{ config.firstNameCapital }} record);

    {{ config.firstNameCapital }} selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective({{ config.firstNameCapital }} record);

    int updateByPrimaryKey({{ config.firstNameCapital }} record);
    
    List<{{ config.firstNameCapital }}> getList{{ config.firstNameCapital }}();
    
    List<{{ config.firstNameCapital }}> findAll{{ config.firstNameCapital }}({{ config.firstNameCapital }} record);
}