/*
 * Copyright (C) 2014-2016 Helix Engine Developers 
 * (http://github.com/fauu/HelixEngine)
 *
 * This software is licensed under the GNU General Public License
 * (version 3 or later). See the COPYING file in this distribution.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this software. If not, see <http://www.gnu.org/licenses/>.
 *
 * Authored by: Piotr Grabowski <fau999@gmail.com>
 */

package com.github.fauu.helix.editor.screen;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.managers.UuidEntityManager;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector3;
import com.github.fauu.helix.editor.manager.CameraIntermediary;
import com.github.fauu.helix.editor.manager.DisplayableIntermediary;
import com.github.fauu.helix.editor.system.CameraControlSystem;
import com.github.fauu.helix.editor.system.TileHighlightingSystem;
import com.github.fauu.helix.editor.system.TilePermissionsEditingSystem;
import com.github.fauu.helix.graphics.HelixCamera;
import com.github.fauu.helix.manager.AreaManager;
import com.github.fauu.helix.manager.LocalAmbienceManager;
import com.github.fauu.helix.manager.TextureManager;
import com.github.fauu.helix.manager.WeatherMan;
import com.github.fauu.helix.system.RenderingSystem;

public class Overworld implements Screen {

  private World world;

  private HelixCamera camera;

  private AssetManager assetManager;

  private DisplayableIntermediary displayableIntermediary;

  private CameraIntermediary cameraIntermediary;

  public Overworld() {
    assetManager = new AssetManager();

    camera = new HelixCamera(60, new Vector3(0, -30, 30), .01f, 300);

    WorldConfiguration worldConfiguration 
        = new WorldConfiguration().register(assetManager)
                                  .register(camera);
    world = new World(worldConfiguration);

    world.setManager(new UuidEntityManager());
    world.setManager(new TextureManager());
    world.setManager(new TagManager());
    world.setManager(new GroupManager());
    world.setManager(new AreaManager());
    world.setManager(new WeatherMan());
    world.setManager(new LocalAmbienceManager());
    world.setManager(displayableIntermediary = new DisplayableIntermediary());
    world.setManager(cameraIntermediary = new CameraIntermediary());

    world.setSystem(new CameraControlSystem());
    world.setSystem(new TileHighlightingSystem());
    world.setSystem(new TilePermissionsEditingSystem());
    world.setSystem(new RenderingSystem());

    world.initialize();
  }

  @Override
  public void render(float delta) {
    world.setDelta(delta);
    world.process();
  }

  @Override
  public void resize(int width, int height) { }

  @Override
  public void show() { }

  @Override
  public void hide() { }

  @Override
  public void pause() { }

  @Override
  public void resume() { }

  @Override
  public void dispose() {
    world.dispose();
    assetManager.dispose();
  }
  
  public World getWorld() {
    return world;
  }
  
  public AssetManager getAssetManager() {
    return assetManager;
  }

  public DisplayableIntermediary getDisplayableIntermediary() {
    return displayableIntermediary;
  }

  public CameraIntermediary getCameraIntermediary() {
    return cameraIntermediary;
  }

}
