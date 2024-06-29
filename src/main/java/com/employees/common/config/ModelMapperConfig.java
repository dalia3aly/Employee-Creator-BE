package com.employees.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.employees.empcreator.Employee.Employee;
import com.employees.empcreator.Employee.dto.CreateEmployeeDTO;
import com.employees.empcreator.Employee.dto.UpdateEmployeeDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter());

        // Create Employee mapping
        mapper.typeMap(CreateEmployeeDTO.class, Employee.class).addMappings(m -> {
            m.map(CreateEmployeeDTO::getContractType, Employee::setContractType);
            m.map(CreateEmployeeDTO::getEmploymentType, Employee::setEmploymentType);
        });

        // Update Employee mapping
        mapper.typeMap(UpdateEmployeeDTO.class, Employee.class).addMappings(m -> {
            m.map(UpdateEmployeeDTO::getContractType, Employee::setContractType);
            m.map(UpdateEmployeeDTO::getEmploymentType, Employee::setEmploymentType);
        });

        return mapper;
    }

    private static class StringTrimConverter implements Converter<String, String> {

        @Override
        public String convert(MappingContext<String, String> context) {
            if (context.getSource() == null) {
                return null;
            }
            return context.getSource().trim();
        }
    }
}
