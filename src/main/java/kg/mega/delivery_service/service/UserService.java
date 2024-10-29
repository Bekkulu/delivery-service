package kg.mega.delivery_service.service;

import kg.mega.delivery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void getUser(Long id){
    try {userRepository.findById(id).orElseThrow();

    }
    catch (Exception e){
        e.printStackTrace();
        }
    }

    public void getAllUsers(){
        userRepository.findAll();
    }

    public void deleteUser(Long id){
        try{userRepository.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
