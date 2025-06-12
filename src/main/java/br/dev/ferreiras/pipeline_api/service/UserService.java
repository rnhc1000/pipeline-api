package br.dev.ferreiras.pipeline_api.service;

import br.dev.ferreiras.pipeline_api.dto.MetaDataDto;
import br.dev.ferreiras.pipeline_api.dto.UserDataDto;
import br.dev.ferreiras.pipeline_api.model.MetaData;
import br.dev.ferreiras.pipeline_api.model.User;
import br.dev.ferreiras.pipeline_api.repository.MetaDataRepository;
import br.dev.ferreiras.pipeline_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MetaDataRepository metaDataRepository;

    public UserService(UserRepository userRepository, MetaDataRepository metaDataRepository) {
        this.userRepository = userRepository;
        this.metaDataRepository = metaDataRepository;
    }


    public User saveUser(UserDataDto userDataDto) {

        User user = new User();
        user.setName(userDataDto.name());
        user.setAddress(userDataDto.address());
        user.setCountry(userDataDto.country());
        user.setId(null);
        List<MetaData> metaData = userDataDto.meta().stream().map(metaDTO -> {
            MetaData meta = new MetaData();
            meta.setCookieKey(metaDTO.cookieKey());
            meta.setCookieValue(metaDTO.cookieValue());
            meta.setUserAgent(metaDTO.userAgent());
            meta.setButtonId(metaDTO.buttonId());
            meta.setTimeStamp(metaDTO.timeStamp());
            meta.setUser(user);
            return meta;
        }).toList();

        user.setMetadata(metaData);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
