/*
 * Copyright (C) 2014, 2015 Helix Engine Developers 
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

package com.github.fauu.helix.editor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.github.fauu.helix.TilePermission;
import com.github.fauu.helix.editor.screen.MainScreen;
import com.github.fauu.helix.editor.state.TilePermissionListState;
import com.github.fauu.helix.editor.state.ToolbarState;
import com.github.fauu.helix.editor.ui.UI;
import com.github.fauu.helix.manager.AreaManager;
import com.google.common.eventbus.EventBus;

public class HelixEditor extends Game {

  private static HelixEditor instance;
  
  private MainScreen mainScreen;
  
  private EventBus editorToUIEventBus;

  private EventBus editorToWorldEventBus;

  private UI ui;

  private ToolbarState toolbarState;

  private TilePermissionListState tilePermissionListState;

  @Override
  public void create() {
    instance = this;
    
    editorToUIEventBus = new EventBus();
    editorToWorldEventBus = new EventBus();
    
    mainScreen = new MainScreen();
    setScreen(mainScreen);

    toolbarState = new ToolbarState();
    toolbarState.initialize(ToolType.TILE_PERMISSIONS);

    tilePermissionListState = new TilePermissionListState();
    tilePermissionListState.initialize(TilePermission.LEVEL0);

    ui = new UI();

    Gdx.input.setInputProcessor(
        new InputMultiplexer(ui.getStage(), new EditorInputEventProcessor()));
  }

  public static HelixEditor getInstance() {
    return instance;
  }
  
  @Override
  public void render() {
    super.render();
    ui.render(Gdx.graphics.getDeltaTime());
  }
  
  @Override
  public void dispose() {
    super.dispose();
    ui.dispose();
  }

  public void closeCurrentMapRegion() {
    mainScreen.getWorld().getManager(AreaManager.class).unloadAll();
  }

  public void openMapRegionAction() {
    ui.showOpenMapRegionFileChooser();
  }

  public void exitAction() {
    Gdx.app.exit();
  }

  public void fadeAreaModelAction(boolean on) {
    mainScreen.getSpatialIntermediary().setAreaSpatialOpacity(on ? 0.5f : 1);
  }

  public void toolSelected(ToolType type) {
    if (type == ToolType.TILE_PERMISSIONS) {
      fadeAreaModelAction(true);
    }
  }

  public EventBus getUIEventBus() {
    return editorToUIEventBus;
  }

  public EventBus getWorldEventBus() {
    return editorToWorldEventBus;
  }
  
  public AssetManager getAssetManager() {
    return mainScreen.getAssetManager();
  }

  public ToolbarState getToolbarState() {
    return toolbarState;
  }

  public TilePermissionListState getTilePermissionListState() {
    return tilePermissionListState;
  }

}
