package winecellar



import org.junit.*
import grails.test.mixin.*

@TestFor(BottleController)
@Mock(Bottle)
class BottleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bottle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bottleInstanceList.size() == 0
        assert model.bottleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bottleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bottleInstance != null
        assert view == '/bottle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bottle/show/1'
        assert controller.flash.message != null
        assert Bottle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bottle/list'

        populateValidParams(params)
        def bottle = new Bottle(params)

        assert bottle.save() != null

        params.id = bottle.id

        def model = controller.show()

        assert model.bottleInstance == bottle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bottle/list'

        populateValidParams(params)
        def bottle = new Bottle(params)

        assert bottle.save() != null

        params.id = bottle.id

        def model = controller.edit()

        assert model.bottleInstance == bottle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bottle/list'

        response.reset()

        populateValidParams(params)
        def bottle = new Bottle(params)

        assert bottle.save() != null

        // test invalid parameters in update
        params.id = bottle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bottle/edit"
        assert model.bottleInstance != null

        bottle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bottle/show/$bottle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bottle.clearErrors()

        populateValidParams(params)
        params.id = bottle.id
        params.version = -1
        controller.update()

        assert view == "/bottle/edit"
        assert model.bottleInstance != null
        assert model.bottleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bottle/list'

        response.reset()

        populateValidParams(params)
        def bottle = new Bottle(params)

        assert bottle.save() != null
        assert Bottle.count() == 1

        params.id = bottle.id

        controller.delete()

        assert Bottle.count() == 0
        assert Bottle.get(bottle.id) == null
        assert response.redirectedUrl == '/bottle/list'
    }
}
