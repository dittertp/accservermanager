package grimsi.accservermanager.backend.service;

import grimsi.accservermanager.backend.dto.InstanceDto;
import grimsi.accservermanager.backend.entity.Instance;
import grimsi.accservermanager.backend.enums.InstanceState;
import grimsi.accservermanager.backend.exception.NotFoundException;
import grimsi.accservermanager.backend.repository.InstanceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanceService {

    @Autowired
    JsonSchemaService jsonSchemaService;

    @Autowired
    ConfigService configService;

    @Autowired
    FileSystemService fileSystemService;

    @Autowired
    InstanceRepository instanceRepository;

    @Autowired
    ModelMapper mapper;

    public List<InstanceDto> findAll() {
        List<Instance> instances = instanceRepository.findAll();
        return mapper.map(instances, new TypeToken<List<InstanceDto>>() {
        }.getType());
    }

    public InstanceDto findById(String id) {
        Instance instance = instanceRepository.findById(id).orElseThrow(NotFoundException::new);
        return convertToDto(instance);
    }

    public List<InstanceDto> findByName(String name){
        List<Instance> instances = instanceRepository.findAllByName(name).orElseThrow(NotFoundException::new);
        return convertToDto(instances);
    }

    public void deleteById(String id) {
        InstanceDto instance = findById(id);
        fileSystemService.deleteInstanceFolder(instance);
        instanceRepository.deleteById(id);
    }

    public InstanceDto updateById(String id, InstanceDto instanceDto) {
        return create(instanceDto);
    }

    public InstanceDto create(InstanceDto instanceDto) {
        Instance instance = convertToEntity(instanceDto);
        instance = instanceRepository.save(instance);

        instanceDto = convertToDto(instance);
        instanceDto.setConfig(configService.findById(instanceDto.getConfig().getId()));

        try{
            fileSystemService.createInstanceFolder(instanceDto);
        } catch (Exception e){
            deleteById(instanceDto.getId());
            throw e;
        }

        return instanceDto;
    }

    public int getActiveInstanceCount(){
        return instanceRepository.findByState(InstanceState.RUNNING).orElseThrow(NotFoundException::new).size();
    }

    public boolean isConfigInUse(String configId){
        return !instanceRepository.findAllByConfig_Id(configId).get().isEmpty();
    }

    public String getJsonSchema() {
        String schema = jsonSchemaService.getJsonSchema(InstanceDto.class);
        if (schema == null) {
            throw new NullPointerException("Error parsing JSON schema");
        }
        return schema;
    }

    private InstanceDto convertToDto(Instance instance) {
        return mapper.map(instance, InstanceDto.class);
    }

    private List<InstanceDto> convertToDto(List<Instance> instanceDtos){
        return instanceDtos.stream().map(instance -> mapper.map(instance, InstanceDto.class)).collect(Collectors.toList());
    }

    private Instance convertToEntity(InstanceDto instanceDto) {
        return mapper.map(instanceDto, Instance.class);
    }

    private List<Instance> convertToEntity(List<Instance> instances) {
        return instances.stream().map(instanceDto -> mapper.map(instanceDto, Instance.class)).collect(Collectors.toList());
    }
}
