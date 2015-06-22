package {{ config.packagePath }}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import {{ config.packagePath }}.persistence.{{ config.firstNameCapital }}Mapper;
import {{ config.packagePath }}.service.{{ config.firstNameCapital }}Service;
import {{ config.packagePath }}.vo.{{ config.firstNameCapital }};
import {{ config.packagePath }}.vo.{{ config.firstNameCapital }}Example;
@Service
public class {{ config.firstNameCapital }}ServiceImpl implements {{ config.firstNameCapital }}Service {

	@Resource
	{{ config.firstNameCapital }}Mapper {{ config.name }}Mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return {{ config.name }}Mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert({{ config.firstNameCapital }} record) {
		return {{ config.name }}Mapper.insert(record);
	}

	@Override
	public int insertSelective({{ config.firstNameCapital }} record) {
		return {{ config.name }}Mapper.insertSelective(record);
	}

	@Override
	public {{ config.firstNameCapital }} selectByPrimaryKey(String id) {
		return {{ config.name }}Mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective({{ config.firstNameCapital }} record) {
		return {{ config.name }}Mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey({{ config.firstNameCapital }} record) {
		return {{ config.name }}Mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<{{ config.firstNameCapital }}> getList{{ config.firstNameCapital }}() {
		{{ config.firstNameCapital }}Example example = new {{ config.firstNameCapital }}Example();
		return {{ config.name }}Mapper.selectByExample(example);
	}

	@Override
	public List<{{ config.firstNameCapital }}> findAll{{ config.firstNameCapital }}({{ config.firstNameCapital }} record) {
		{{ config.firstNameCapital }}Example example = new {{ config.firstNameCapital }}Example();
		return {{ config.name }}Mapper.selectByExample(example);
	}

}
