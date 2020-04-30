package com.example.api.dto;

import com.example.api.dao.entity.BaseUser;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BaseUserMapper {

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "name", target = "name"),
			@Mapping(source = "password", target = "password"),
			@Mapping(source = "role", target = "role"),
	})
	BaseUserDTO toDTO(BaseUser entity);

	List<BaseUserDTO> toDTO(List<BaseUser> entities);

	@InheritInverseConfiguration
	BaseUser toEntity(BaseUserDTO dto);
}
