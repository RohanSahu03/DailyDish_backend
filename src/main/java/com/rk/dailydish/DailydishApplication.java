package com.rk.dailydish;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rk.dailydish.config.AppConstant;
import com.rk.dailydish.entity.Role;
import com.rk.dailydish.repository.RoleRepo;

@SpringBootApplication
public class DailydishApplication implements CommandLineRunner{
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(DailydishApplication.class, args);
	}
	
	@Bean
	 ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			Role role = new Role();
			role.setId(AppConstant.NORMAL_USER);
			role.setName("NORMAL_USER");
			
			Role role1 = new Role();
			role1.setId(AppConstant.ADMIN_USER);
			role1.setName("ADMIN_USER");
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> saveAll = this.roleRepo.saveAll(roles);
			saveAll.forEach(r->{
				System.out.println(r.getName());
				
			});
			
		}catch(Exception e) {
System.out.println("ddfd");	
		}
	}

}
