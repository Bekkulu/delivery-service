//package kg.mega.delivery_service.config;
//
//import kg.mega.delivery_service.enums.Role;
//import kg.mega.delivery_service.model.entity.User;
//import kg.mega.delivery_service.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Component
//public class InitialAdminSetup {
//
//
//    @Bean
//    @Transactional
//    public CommandLineRunner createAdmin (UserRepository userRepository,
//                                          PasswordEncoder passwordEncoder) {
//        return args -> {
//                User admin = new User();
//                admin.setEmail("admin@gmail.com");
//                admin.setUsername("admin");
//                admin.setPhone("123466");
//                admin.setPassword(passwordEncoder.encode("password")); // Убедитесь, что пароль безопасный
//                admin.setRole(Role.ADMIN);
//                userRepository.save(admin);
//
//                System.out.println("Admin user created!");
//        };
//    }
//}
