package com.canoacaicara.groups.usecases;

import java.util.List;

public class EnrollUserInteractor {

    public EnrollUserInteractor(){}

    // EM DESENVOLVIMENTO
    public void enrollUser(Integer userId, List<String> enrolledGroupsList){

        for(String enrolledGroupId : enrolledGroupsList){

            // criar registro no banco de dados passando userId e enrolledGroupId
            // atenção: tabela groups_users ainda não foi criada
        }

    }
}
