package com.aliens.CustomerManagement.service;

import com.aliens.CustomerManagement.model.Customer;
import com.aliens.CustomerManagement.repository.CustomerRepository;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SparkService {
    @Autowired
    private JavaSparkContext jsc;
    private final SparkSession sparkSession;
    private final String uri;

    @Autowired
    public SparkService(SparkSession sparkSession, @Value("${spring.data.mongodb.uri}") String uri) {
        this.sparkSession = sparkSession;
        this.uri = uri;
    }

    @Autowired
    CustomerRepository customerRepository;

    public long countAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        Dataset<Row> customerDF = sparkSession.createDataFrame(customers, Customer.class);

        long count = customerDF.count();

        return count;
    }

    public String performDataProfiling() {
        List<Customer> customers = customerRepository.findAll();

        // Step 2: Convert List<Customer> to Dataset<Row>
        Dataset<Row> customerDF = sparkSession.createDataFrame(customers, Customer.class);

        // Step 3: Perform data profiling
        String profilingResults = generateProfilingStatistics(customerDF);

        return profilingResults;
    }

    private String generateProfilingStatistics(Dataset<Row> customerDF) {
        String profilingResults = "Data Profiling Results:\n";
        profilingResults += "Number of Records: " + customerDF.count() + "\n";
        profilingResults += "Schema: \n";
        profilingResults += customerDF.schema().treeString() + "\n";

        // You can add more profiling statistics as needed

        return profilingResults;
    }

    public String performMissingValueHandlingAndDataProfiling() {
        List<Customer> customers = customerRepository.findAll();

        Dataset<Row> customerDF = sparkSession.createDataFrame(customers, Customer.class);
        Dataset<Row> cleanedData = handleMissingValues(customerDF);
        String profilingResults = ProfilingStatistics(cleanedData);

        return profilingResults;
    }

    private Dataset<Row> handleMissingValues(Dataset<Row> customerDF) {
        return customerDF.na().fill(0);
    }

    private String ProfilingStatistics(Dataset<Row> customerDF) {
        if (customerDF != null) {
            StringBuilder profilingResults = new StringBuilder("Data Profiling Results:\n");
            long recordCount = customerDF.count();
            if (recordCount > 0) {
                profilingResults.append("Number of Records: ").append(recordCount).append("\n");
                profilingResults.append("Schema: \n").append(customerDF.schema().treeString()).append("\n");
                // Add more profiling statistics as needed
                return profilingResults.toString();
            } else {
                return "No data available for profiling.";
            }
        } else {
            return "Failed to retrieve data.";
        }
    }
}