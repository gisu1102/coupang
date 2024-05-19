package com.example.coupang.service;

import com.example.coupang.dto.MemberAddressDTO;
import com.example.coupang.entity.MemberAddressEntity;
import com.example.coupang.entity.MemberEntity;
import com.example.coupang.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberAddressService {
    private final AddressRepository addressRepository;


    public void addAddress(MemberAddressDTO addressDTO) {
        MemberAddressEntity addressEntity = new MemberAddressEntity();
        addressEntity.setZipcode(addressDTO.getZipcode());
        addressEntity.setAddressDetail(addressDTO.getAddressDetail());

        // memberId로 멤버 엔티티 찾아서 설정
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(addressDTO.getMemberId());
        addressEntity.setMemberEntity(memberEntity);

        addressRepository.save(addressEntity);
    }

    public List<MemberAddressDTO> getAllAddressesByMemberId(Long memberId) {
        List<MemberAddressEntity> addressEntities = addressRepository.findByMemberEntityId(memberId);
        List<MemberAddressDTO> addressDTOs = new ArrayList<>();

        for (MemberAddressEntity addressEntity : addressEntities) {
            MemberAddressDTO addressDTO = new MemberAddressDTO();
            addressDTO.setId(addressEntity.getId());
            addressDTO.setZipcode(addressEntity.getZipcode());
            addressDTO.setAddressDetail(addressEntity.getAddressDetail());
            // 기타 필요한 필드 설정

            addressDTOs.add(addressDTO);
        }

        return addressDTOs;
    }
}
