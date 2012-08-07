package winecellar

import org.springframework.dao.DataIntegrityViolationException

class VarietalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [varietalInstanceList: Varietal.list(params), varietalInstanceTotal: Varietal.count()]
    }

    def create() {
        [varietalInstance: new Varietal(params)]
    }

    def save() {
        def varietalInstance = new Varietal(params)
        if (!varietalInstance.save(flush: true)) {
            render(view: "create", model: [varietalInstance: varietalInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'varietal.label', default: 'Varietal'), varietalInstance.id])
        redirect(action: "show", id: varietalInstance.id)
    }

    def show(Long id) {
        def varietalInstance = Varietal.get(id)
        if (!varietalInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'varietal.label', default: 'Varietal'), id])
            redirect(action: "list")
            return
        }

        [varietalInstance: varietalInstance]
    }

    def edit(Long id) {
        def varietalInstance = Varietal.get(id)
        if (!varietalInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'varietal.label', default: 'Varietal'), id])
            redirect(action: "list")
            return
        }

        [varietalInstance: varietalInstance]
    }

    def update(Long id, Long version) {
        def varietalInstance = Varietal.get(id)
        if (!varietalInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'varietal.label', default: 'Varietal'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (varietalInstance.version > version) {
                varietalInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'varietal.label', default: 'Varietal')] as Object[],
                          "Another user has updated this Varietal while you were editing")
                render(view: "edit", model: [varietalInstance: varietalInstance])
                return
            }
        }

        varietalInstance.properties = params

        if (!varietalInstance.save(flush: true)) {
            render(view: "edit", model: [varietalInstance: varietalInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'varietal.label', default: 'Varietal'), varietalInstance.id])
        redirect(action: "show", id: varietalInstance.id)
    }

    def delete(Long id) {
        def varietalInstance = Varietal.get(id)
        if (!varietalInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'varietal.label', default: 'Varietal'), id])
            redirect(action: "list")
            return
        }

        try {
            varietalInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'varietal.label', default: 'Varietal'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'varietal.label', default: 'Varietal'), id])
            redirect(action: "show", id: id)
        }
    }
}
