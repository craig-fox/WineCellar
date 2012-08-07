package winecellar



import org.junit.*
import grails.test.mixin.*

@TestFor(WineryController)
@Mock(Winery)
class WineryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/winery/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.wineryInstanceList.size() == 0
        assert model.wineryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.wineryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.wineryInstance != null
        assert view == '/winery/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/winery/show/1'
        assert controller.flash.message != null
        assert Winery.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/winery/list'

        populateValidParams(params)
        def winery = new Winery(params)

        assert winery.save() != null

        params.id = winery.id

        def model = controller.show()

        assert model.wineryInstance == winery
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/winery/list'

        populateValidParams(params)
        def winery = new Winery(params)

        assert winery.save() != null

        params.id = winery.id

        def model = controller.edit()

        assert model.wineryInstance == winery
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/winery/list'

        response.reset()

        populateValidParams(params)
        def winery = new Winery(params)

        assert winery.save() != null

        // test invalid parameters in update
        params.id = winery.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/winery/edit"
        assert model.wineryInstance != null

        winery.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/winery/show/$winery.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        winery.clearErrors()

        populateValidParams(params)
        params.id = winery.id
        params.version = -1
        controller.update()

        assert view == "/winery/edit"
        assert model.wineryInstance != null
        assert model.wineryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/winery/list'

        response.reset()

        populateValidParams(params)
        def winery = new Winery(params)

        assert winery.save() != null
        assert Winery.count() == 1

        params.id = winery.id

        controller.delete()

        assert Winery.count() == 0
        assert Winery.get(winery.id) == null
        assert response.redirectedUrl == '/winery/list'
    }
}
