package winecellar



import org.junit.*
import grails.test.mixin.*

@TestFor(WineController)
@Mock(Wine)
class WineControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/wine/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.wineInstanceList.size() == 0
        assert model.wineInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.wineInstance != null
    }

    void testSave() {
        controller.save()

        assert model.wineInstance != null
        assert view == '/wine/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/wine/show/1'
        assert controller.flash.message != null
        assert Wine.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/wine/list'

        populateValidParams(params)
        def wine = new Wine(params)

        assert wine.save() != null

        params.id = wine.id

        def model = controller.show()

        assert model.wineInstance == wine
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/wine/list'

        populateValidParams(params)
        def wine = new Wine(params)

        assert wine.save() != null

        params.id = wine.id

        def model = controller.edit()

        assert model.wineInstance == wine
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/wine/list'

        response.reset()

        populateValidParams(params)
        def wine = new Wine(params)

        assert wine.save() != null

        // test invalid parameters in update
        params.id = wine.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/wine/edit"
        assert model.wineInstance != null

        wine.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/wine/show/$wine.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        wine.clearErrors()

        populateValidParams(params)
        params.id = wine.id
        params.version = -1
        controller.update()

        assert view == "/wine/edit"
        assert model.wineInstance != null
        assert model.wineInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/wine/list'

        response.reset()

        populateValidParams(params)
        def wine = new Wine(params)

        assert wine.save() != null
        assert Wine.count() == 1

        params.id = wine.id

        controller.delete()

        assert Wine.count() == 0
        assert Wine.get(wine.id) == null
        assert response.redirectedUrl == '/wine/list'
    }
}
