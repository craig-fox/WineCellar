package winecellar

import org.springframework.dao.DataIntegrityViolationException

class WineryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [wineryInstanceList: Winery.list(params), wineryInstanceTotal: Winery.count()]
    }

    def create() {
        [wineryInstance: new Winery(params)]
    }

    def save() {
        def wineryInstance = new Winery(params)
        if (!wineryInstance.save(flush: true)) {
            render(view: "create", model: [wineryInstance: wineryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'winery.label', default: 'Winery'), wineryInstance.id])
        redirect(action: "show", id: wineryInstance.id)
    }

    def show(Long id) {
        def wineryInstance = Winery.get(id)
        if (!wineryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'winery.label', default: 'Winery'), id])
            redirect(action: "list")
            return
        }

        [wineryInstance: wineryInstance]
    }

    def edit(Long id) {
        def wineryInstance = Winery.get(id)
        if (!wineryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'winery.label', default: 'Winery'), id])
            redirect(action: "list")
            return
        }

        [wineryInstance: wineryInstance]
    }

    def update(Long id, Long version) {
        def wineryInstance = Winery.get(id)
        if (!wineryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'winery.label', default: 'Winery'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (wineryInstance.version > version) {
                wineryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'winery.label', default: 'Winery')] as Object[],
                          "Another user has updated this Winery while you were editing")
                render(view: "edit", model: [wineryInstance: wineryInstance])
                return
            }
        }

        wineryInstance.properties = params

        if (!wineryInstance.save(flush: true)) {
            render(view: "edit", model: [wineryInstance: wineryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'winery.label', default: 'Winery'), wineryInstance.id])
        redirect(action: "show", id: wineryInstance.id)
    }

    def delete(Long id) {
        def wineryInstance = Winery.get(id)
        if (!wineryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'winery.label', default: 'Winery'), id])
            redirect(action: "list")
            return
        }

        try {
            wineryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'winery.label', default: 'Winery'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'winery.label', default: 'Winery'), id])
            redirect(action: "show", id: id)
        }
    }
}
