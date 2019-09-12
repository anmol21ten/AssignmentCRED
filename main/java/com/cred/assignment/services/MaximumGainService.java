package com.cred.assignment.services;

import com.cred.assignment.exchanges.MaximumGainRequest;
import com.cred.assignment.exchanges.MaximumGainResponse;

public interface MaximumGainService {
    MaximumGainResponse getMaximumGain(MaximumGainRequest maximumGainRequest);
}
