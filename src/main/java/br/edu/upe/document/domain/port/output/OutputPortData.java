package br.edu.upe.document.domain.port.output;

import java.util.Map;

/**
 * Output Port interface responsible for retrieving auxiliary institutional data.
 * <p>
 * In the context of Hexagonal Architecture, this port allows the domain service to
 * request external static or dynamic data required to enrich documents
 * (such as insurance policy details, dean's name, or specific campus addresses)
 * without coupling the domain to the data source (Database, API, or Configuration properties).
 * </p>
 */
public interface OutputPortData {

    /**
     * Retrieves a set of institutional data specific to a given campus.
     * <p>
     * This method is used to populate placeholders in the document that are not
     * provided by the user request but are system-defined or persistent.
     * Examples include:
     * <ul>
     * <li>Insurance Company Name and Policy Number (e.g., AS_NOME, AS_NUMERO)</li>
     * <li>Campus Address and CNPJ (e.g., IE_ENDERECO, IE_CNPJ)</li>
     * <li>Legal Representative or Dean's Name</li>
     * </ul>
     * </p>
     *
     * @param campus The identifier or name of the campus (e.g., "Petrolina", "Santo Amaro")
     * used to filter the specific institutional data.
     * @return A {@code Map<String, String>} containing the data keys (matching the template placeholders)
     * and their corresponding values. Returns an empty map or null if no data is found,
     * depending on the implementation.
     */
    Map<String, String> getInsurancePolicyData(String campus);

}