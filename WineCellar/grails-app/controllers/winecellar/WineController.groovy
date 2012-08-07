package winecellar

import org.springframework.dao.DataIntegrityViolationException

class WineController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [wineInstanceList: Wine.list(params), wineInstanceTotal: Wine.count()]
    }

    def create() {
        [wineInstance: new Wine(params)]
    }

    def save() {
        def wineInstance = new Wine(params)
        if (!wineInstance.save(flush: true)) {
            render(view: "create", model: [wineInstance: wineInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'wine.label', default: 'Wine'), wineInstance.id])
        redirect(action: "show", id: wineInstance.id)
    }

    def show(Long id) {
        def wineInstance = Wine.get(id)
        if (!wineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wine.label', default: 'Wine'), id])
            redirect(action: "list")
            return
        }

        [wineInstance: wineInstance]
    }

    def edit(Long id) {
        def wineInstance = Wine.get(id)
        if (!wineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wine.label', default: 'Wine'), id])
            redirect(action: "list")
            return
        }

        [wineInstance: wineInstance]
    }

    def update(Long id, Long version) {
        def wineInstance = Wine.get(id)
        if (!wineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wine.label', default: 'Wine'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (wineInstance.version > version) {
                wineInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'wine.label', default: 'Wine')] as Object[],
                          "Another user has updated this Wine while you were editing")
                render(view: "edit", model: [wineInstance: wineInstance])
                return
            }
        }

        wineInstance.properties = params

        if (!wineInstance.save(flush: true)) {
            render(view: "edit", model: [wineInstance: wineInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'wine.label', default: 'Wine'), wineInstance.id])
        redirect(action: "show", id: wineInstance.id)
    }

    def delete(Long id) {
        def wineInstance = Wine.get(id)
        if (!wineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wine.label', default: 'Wine'), id])
            redirect(action: "list")
            return
        }

        try {
            wineInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'wine.label', default: 'Wine'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'wine.label', default: 'Wine'), id])
            redirect(action: "show", id: id)
        }
    }
}
