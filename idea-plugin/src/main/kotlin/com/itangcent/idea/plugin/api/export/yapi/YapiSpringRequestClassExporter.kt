package com.itangcent.idea.plugin.api.export.yapi

import com.itangcent.common.model.Request
import com.itangcent.idea.plugin.api.export.SpringRequestClassExporter
import com.itangcent.idea.plugin.rule.computer
import com.itangcent.intellij.jvm.element.ExplicitMethod

class YapiSpringRequestClassExporter : SpringRequestClassExporter() {
    override fun processCompleted(method: ExplicitMethod, request: Request) {
        super.processCompleted(method, request)

        val tags = ruleComputer!!.computer(YapiClassExportRuleKeys.TAG, method)
        if (!tags.isNullOrBlank()) {
            request.setTags(tags.split("\n")
                    .map { it.trim() }
                    .filter { it.isNotBlank() })
        }

        val status = ruleComputer.computer(YapiClassExportRuleKeys.STATUS, method)
        request.setStatus(status)
    }
}