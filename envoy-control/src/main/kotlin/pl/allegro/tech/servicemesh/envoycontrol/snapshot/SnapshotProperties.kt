@file:Suppress("MagicNumber")

package pl.allegro.tech.servicemesh.envoycontrol.snapshot

import java.time.Duration

class SnapshotProperties {
    var routes = RoutesProperties()
    var localService = LocalServiceProperties()
    var egress = EgressProperties()
    var incomingPermissions = IncomingPermissionsProperties()
    var outgoingPermissions = OutgoingPermissionsProperties()
    var clusterOutlierDetection = ClusterOutlierDetectionProperties()
    var xdsClusterName = "envoy-control-xds"
    var edsConnectionTimeout: Duration = Duration.ofSeconds(2)
    var stateSampleDuration: Duration = Duration.ofSeconds(1)
    var staticClusterConnectionTimeout: Duration = Duration.ofSeconds(2)
    var trustedCaFile = "/etc/ssl/certs/ca-certificates.crt"
}

class OutgoingPermissionsProperties {
    var enabled = false
    var allServicesDependenciesValue = "*"
    var servicesAllowedToUseWildcard: MutableSet<String> = mutableSetOf()
}

class IncomingPermissionsProperties {
    var enabled = false
    /**
     * unavailable = not found || unauthorized
     */
    var endpointUnavailableStatusCode = 503
    var clientIdentityHeader = "x-service-name"
}

class RoutesProperties {
    var metrics = MetricsRouteProperties()
    var status = StatusRouteProperties()
}

class ClusterOutlierDetectionProperties {
    var enabled = false
    var consecutive5xx = 5
    var interval: Duration = Duration.ofSeconds(10)
    var baseEjectionTime: Duration = Duration.ofSeconds(30)
    var maxEjectionPercent = 10
    var enforcingConsecutive5xx = 100
    var enforcingSuccessRate = 100
    var successRateMinimumHosts = 5
    var successRateRequestVolume = 100
    var successRateStdevFactor = 1900
    var consecutiveGatewayFailure = 5
    var enforcingConsecutiveGatewayFailure = 0
}

class MetricsRouteProperties {
    var enabled = false
    var pathPrefix = "/status/envoy/stats/prometheus"
}

class StatusRouteProperties {
    var enabled = false
    var pathPrefix = "/status/"
    var createVirtualCluster = false
}

class LocalServiceProperties {
    var idleTimeout: Duration = Duration.ofSeconds(60)
    var responseTimeout: Duration = Duration.ofSeconds(15)
    var retryPolicy: RetryPoliciesProperties = RetryPoliciesProperties()
}

class RetryPoliciesProperties {
    var default: RetryPolicyProperties = RetryPolicyProperties()
    var perHttpMethod: MutableMap<String, RetryPolicyProperties> = mutableMapOf()
}

class RetryPolicyProperties {
    var enabled = false
    var retryOn: MutableSet<String> = mutableSetOf()
    var numRetries: Int = 1
    var perTryTimeout: Duration = Duration.ofMillis(0)
    var hostSelectionRetryMaxAttempts: Long = 1
    var retriableStatusCodes: MutableSet<Int> = mutableSetOf()
}

class EgressProperties {
    var clusterNotFoundStatusCode = 503
}