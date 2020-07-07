package pl.allegro.tech.servicemesh.envoycontrol.snapshot.listeners.filters

import pl.allegro.tech.servicemesh.envoycontrol.snapshot.SnapshotProperties
import pl.allegro.tech.servicemesh.envoycontrol.snapshot.listeners.HttpFilterFactory

class EnvoyHttpFilters(
    val ingressFilters: List<HttpFilterFactory>,
    val egressFilters: List<HttpFilterFactory>
) {
    companion object {
        val emptyFilters = EnvoyHttpFilters(listOf(), listOf())

        fun defaultFilters(
            snapshotProperties: SnapshotProperties,
            luaIngressFilterFactory: LuaFilterFactory
        ): EnvoyHttpFilters {
            val defaultFilters = EnvoyDefaultFilters(snapshotProperties, luaIngressFilterFactory)
            return EnvoyHttpFilters(defaultFilters.defaultIngressFilters, defaultFilters.defaultEgressFilters)
        }
    }
}
