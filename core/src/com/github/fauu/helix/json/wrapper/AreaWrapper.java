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

package com.github.fauu.helix.json.wrapper;

import com.github.fauu.helix.AreaType;

import java.util.ArrayList;

public class AreaWrapper {

  public int width;

  public int length;

  public AreaType type;
  
  public ArrayList<TileWrapper> tiles;

}
