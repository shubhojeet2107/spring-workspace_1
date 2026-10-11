
package com.wildlife.staffresourceservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.staffresourceservice.entity.Resource;
import com.wildlife.staffresourceservice.repository.ResourceRepository;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource addResource(Resource resource) {
        resource.setResourceId(null);
        return resourceRepository.save(resource);
    }

    public Resource getResourceById(Long id) {
        List<Resource> resources = resourceRepository.findAll();

        for (Resource resource : resources) {
            if (resource.getResourceId().equals(id)) {
                return resource;
            }
        }

        return null;
    }

    public Resource updateResource(Long id, Resource updatedResource) {
        List<Resource> resources = resourceRepository.findAll();

        for (Resource resource : resources) {
            if (resource.getResourceId().equals(id)) {
                resource.setResourceName(updatedResource.getResourceName());
                resource.setResourceType(updatedResource.getResourceType());
                resource.setQuantity(updatedResource.getQuantity());
                resource.setAssignedArea(updatedResource.getAssignedArea());
                resource.setStatus(updatedResource.getStatus());

                return resourceRepository.save(resource);
            }
        }

        return null;
    }

    public boolean deleteResource(Long id) {
        List<Resource> resources = resourceRepository.findAll();

        for (Resource resource : resources) {
            if (resource.getResourceId().equals(id)) {
                resourceRepository.delete(resource);
                return true;
            }
        }

        return false;
    }
}
