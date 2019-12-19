package ${package}.${module}.service;


import com.simple4j.common.api.bean.ApiRequest;
import com.simple4j.common.api.bean.ApiResponse;
import com.simple4j.common.api.expection.BusinessException;

<#list methods?keys as key>
    import ${package}.${module}.request.${key?cap_first}Request;
    import ${package}.${module}.response.${key?cap_first}Response;
</#list>

/**
* ${r'<'}p${r'>'}
* ${api_comment}
* ${r'<'}/p${r'>'}
*
* @author ${author}
* @since ${date}
*/
public interface ${module?cap_first}Service {

<#list methods?keys as key>
    /**
    * ${methods[key]}
    *
    * @param request
    * @return
    * @throws BusinessException
    */
    ApiResponse${r'<'}${key?cap_first}Response${r'>'} ${key}(ApiRequest${r'<'}${key?cap_first}Request${r'>'} request) throws BusinessException;

</#list>
}
