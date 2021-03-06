package com.skillbox.javapro21.service.impl;

import com.skillbox.javapro21.api.response.ListDataResponse;
import com.skillbox.javapro21.api.response.platform.LanguageData;
import com.skillbox.javapro21.domain.Language;
import com.skillbox.javapro21.repository.LanguageRepository;
import com.skillbox.javapro21.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final UtilsService utilsService;

    @Override
    @Cacheable(value = "languages")
    public ListDataResponse<LanguageData> getLanguage(String language, int offset, int itemPerPage) {
        log.info("caching languages");
        Pageable pageable = PageRequest.of(offset, itemPerPage);
        Page<Language> pageableLanguageList = languageRepository.findAll(pageable);
        return getLanguageResponse(offset, itemPerPage, pageableLanguageList);
    }

    private ListDataResponse<LanguageData> getLanguageResponse(int offset, int itemPerPage, Page<Language> languages) {
        ListDataResponse<LanguageData> languageDataListDataResponse = new ListDataResponse<>();
        languageDataListDataResponse.setError("ok");
        languageDataListDataResponse.setOffset(offset);
        languageDataListDataResponse.setPerPage(itemPerPage);
        languageDataListDataResponse.setTotal((int) languages.getTotalElements());
        languageDataListDataResponse.setTimestamp(utilsService.getTimestampFromLocalDateTime(LocalDateTime.now()));
        languageDataListDataResponse.setData(getLanguagesForResponse(languages.toList()));
        return languageDataListDataResponse;
    }

    private List<LanguageData> getLanguagesForResponse(List<Language> languages) {
        List<LanguageData> languagesData = new ArrayList<>();
        languages.forEach(language -> {
            languagesData.add(getLanguageData(language));
        });

        return languagesData;
    }

    private LanguageData getLanguageData(Language language) {
        return new LanguageData()
                .setId(language.getId())
                .setTypeId(language.getTypeId())
                .setEntityId(language.getEntityId())
                .setSentTime(language.getSentTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .setInfo(language.getInfo());
    }
}
