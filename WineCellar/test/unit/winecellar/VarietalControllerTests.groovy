package winecellar



import org.junit.*
import grails.test.mixin.*

@TestFor(VarietalController)
@Mock(Varietal)
class VarietalControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/varietal/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.varietalInstanceList.size() == 0
        assert model.varietalInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.varietalInstance != null
    }

    void testSave() {
        controller.save()

        assert model.varietalInstance != null
        assert view == '/varietal/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/varietal/show/1'
        assert controller.flash.message != null
        assert Varietal.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/varietal/list'

        populateValidParams(params)
        def varietal = new Varietal(params)

        assert varietal.save() != null

        params.id = varietal.id

        def model = controller.show()

        assert model.varietalInstance == varietal
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/varietal/list'

        populateValidParams(params)
        def varietal = new Varietal(params)

        assert varietal.save() != null

        params.id = varietal.id

        def model = controller.edit()

        assert model.varietalInstance == varietal
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/varietal/list'

        response.reset()

        populateValidParams(params)
        def varietal = new Varietal(params)

        assert varietal.save() != null

        // test invalid parameters in update
        params.id = varietal.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/varietal/edit"
        assert model.varietalInstance != null

        varietal.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/varietal/show/$varietal.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        varietal.clearErrors()

        populateValidParams(params)
        params.id = varietal.id
        params.version = -1
        controller.update()

        assert view == "/varietal/edit"
        assert model.varietalInstance != null
        assert model.varietalInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/varietal/list'

        response.reset()

        populateValidParams(params)
        def varietal = new Varietal(params)

        assert varietal.save() != null
        assert Varietal.count() == 1

        params.id = varietal.id

        controller.delete()

        assert Varietal.count() == 0
        assert Varietal.get(varietal.id) == null
        assert response.redirectedUrl == '/varietal/list'
    }
}
