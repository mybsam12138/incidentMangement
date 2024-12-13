package com.sam.business.service;

import com.sam.business.exception.BusinessException;
import com.sam.business.model.Incident;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {
    private final Map<String, Incident> incidentMap = new HashMap<>();

    // 创建事件
    public Incident createIncident(Incident incident) {
        if (incidentMap.containsKey(incident.getId())) {
            throw new BusinessException("Incident with this ID already exists");
        }
        incidentMap.put(incident.getId(), incident);
        return incident;
    }

    // 修改事件
    public Incident updateIncident(Incident updatedIncident) {
        String id=updatedIncident.getId();
        if (!incidentMap.containsKey(id)) {
            throw new BusinessException("Incident not found with ID: " + id);
        }
        updatedIncident.setId(id);
        incidentMap.put(id, updatedIncident);
        return updatedIncident;
    }

    // 删除事件
    public void deleteIncident(String id) {
        if (!incidentMap.containsKey(id)) {
            throw new BusinessException("Incident not found with ID: " + id);
        }
        incidentMap.remove(id);
    }

    // 查询所有事件（分页）
    public List<Incident> getAllIncidents(int page, int size) {
        List<Incident> incidents = new ArrayList<>(incidentMap.values());
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, incidents.size());
        if (fromIndex >= incidents.size()) {
            return Collections.emptyList();
        }
        return incidents.subList(fromIndex, toIndex);
    }
}